import { programStaticData } from "../data/data.ts";
import type { Program } from "../types/program.types";
import { httpGet } from "./client.ts";

const BASE_URL = (import.meta.env.VITE_API_BASE_URL || "").replace(/\/+$/, "");

const PROGRAMS_URL = `${BASE_URL}/programs`;

interface ProgramSearchResponse {
  programs: Program[];
  totalFound: number;
  page: number;
  perPage: number;
}

export const fetchPrograms = async (
  query: string,
  page: number = 1,
  perPage: number = 10
): Promise<Program[]> => {
  console.log(
    `Fetching programs with query: ${query}, page: ${page}, perPage: ${perPage}`
  );

  const params = new URLSearchParams();
  if (query?.trim()) params.append("query", query.trim());
  params.append("page", String(page));
  params.append("perPage", String(perPage));

  const url = `${PROGRAMS_URL}?${params.toString()}`;
  const retry = { retries: 2, backoffBaseMs: 250, backoffMaxMs: 2000 };
  const programs = (
    await httpGet<ProgramSearchResponse>(url, { timeoutMs: 20_000 }, retry)
  ).programs;
  console.log(`Fetched programs: ${JSON.stringify(programs)}`);

  return programs;
};

export async function fetchProgramById(
  programId: number
): Promise<Program | undefined> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const program = programStaticData.find((p) => p.id === programId);
      resolve(program);
    }, 100);
  });
}
