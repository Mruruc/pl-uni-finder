
import { request, type HttpRequestOptions, type RetryOptions } from "./http-util";

export const httpGet = <T>(
  path: string,
  opts?: HttpRequestOptions,
  retry?: RetryOptions
) => request<T>("GET", path, opts, retry);

export const httpPost = <T>(
  path: string,
  body?: unknown,
  opts?: HttpRequestOptions,
  retry?: RetryOptions
) => request<T>("POST", path, { ...opts, body }, retry);

export const httpPut = <T>(
  path: string,
  body?: unknown,
  opts?: HttpRequestOptions,
  retry?: RetryOptions
) => request<T>("PUT", path, { ...opts, body }, retry);

export const httpDelete = <T>(
  path: string,
  opts?: HttpRequestOptions,
  retry?: RetryOptions
) => request<T>("DELETE", path, opts, retry);
