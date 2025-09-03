const DEFAULT_TIMEOUT_MS = 12_000;

export class HttpError extends Error {
  status: number;
  body?: string;
  constructor(message: string, status: number, body?: string) {
    super(message);
    this.status = status;
    this.body = body;
  }
}

export interface HttpRequestOptions {
  headers?: Record<string, string>;
  body?: unknown;
  signal?: AbortSignal;
  timeoutMs?: number;
}

export interface RetryOptions {
  retries?: number;
  backoffBaseMs?: number;
  backoffMaxMs?: number;
}

const sleep = (ms: number) =>
  new Promise((r) => setTimeout(r, ms + Math.random() * 120));

const isJson = (contentType?: string | null) =>
  (contentType || "").toLowerCase().includes("application/json");

export async function request<T>(
  method: "GET" | "POST" | "PUT" | "PATCH" | "DELETE",
  path: string,
  req: HttpRequestOptions = {},
  retry?: RetryOptions
): Promise<T> {
  const headers: Record<string, string> = {
    Accept: "application/json",
    ...(req.headers || {}),
  };

  const bodyInit = resolveReqBody(req, headers);

  const doFetch = async () => {
    const res = await rawFetch(
      path,
      { method, headers, body: bodyInit },
      req.timeoutMs ?? DEFAULT_TIMEOUT_MS,
      req.signal
    );

    if (!res.ok) {
      const ct = res.headers.get("content-type");
      const text =
        ct && isJson(ct)
          ? JSON.stringify(await res.json().catch(() => ({})))
          : await res.text().catch(() => "");
      throw new HttpError(text || res.statusText, res.status, text);
    }

    const ct = res.headers.get("content-type");
    if (ct && isJson(ct)) return (await res.json()) as T;

    return undefined as unknown as T;
  };

  return retry ? withRetry(doFetch, retry) : doFetch();
}

async function rawFetch(
  url: string,
  init: RequestInit,
  timeoutMs: number,
  externalSignal?: AbortSignal
): Promise<Response> {
  const controller = new AbortController();
  if (externalSignal?.aborted) {
    controller.abort();
  }
  const onAbort = () => controller.abort();
  externalSignal?.addEventListener("abort", onAbort);

  const timer = setTimeout(() => controller.abort(), timeoutMs);

  try {
    return await fetch(url, {
      ...init,
      signal: controller.signal,
    });
  } finally {
    clearTimeout(timer);
    if (externalSignal) externalSignal.removeEventListener("abort", onAbort);
  }
}

async function withRetry<T>(
  op: () => Promise<T>,
  { retries = 2, backoffBaseMs = 250, backoffMaxMs = 2000 }: RetryOptions = {}
): Promise<T> {
  let attempt = 0;

  while (true) {
    try {
      attempt += 1;
      return await op();
    } catch (err: any) {
      const status = err instanceof HttpError ? err.status : undefined;
      const isAbort = err?.name === "AbortError";
      const isRetriable =
        !isAbort &&
        (status === undefined ||
          status === 429 ||
          (status >= 500 && status <= 599));

      if (!isRetriable || attempt > retries + 1) throw err;

      const backoff = Math.min(backoffMaxMs, backoffBaseMs * attempt * attempt);
      await sleep(backoff);
    }
  }
}

function resolveReqBody(
  req: HttpRequestOptions,
  headers: Record<string, string>
): BodyInit | undefined {
  let bodyInit: BodyInit | undefined;
  if (req.body !== undefined && req.body !== null) {
    if (req.body instanceof FormData || req.body instanceof Blob) {
      bodyInit = req.body as any;
    } else {
      headers["Content-Type"] = headers["Content-Type"] || "application/json";
      bodyInit = JSON.stringify(req.body);
    }
  }
  return bodyInit;
}
