import { createSelector } from "@reduxjs/toolkit";
import type { RootState } from "../../store/store";
import type { Language } from "../../types/filter.types";
import type { Program } from "../../types/program.types";

const matchesCategory = (value: string, filter: string) => {
  if (!filter || filter === "all" || filter === "any") return true;
  return (value || "").toLowerCase() === filter.toLowerCase();
};


const matchesLanguage = (
  programLang: string | string[] | undefined,
  filter: Language
) => {
  if (filter === "any") return true;
  if (!programLang) return false;

  const normalized = Array.isArray(programLang)
    ? programLang.map((x) => x.toLowerCase())
    : [String(programLang).toLowerCase()];

  if (filter === "both") {
    return normalized.includes("english") && normalized.includes("polish");
  }
  return normalized.includes(filter.toLowerCase());
};


const matchesTuition = (
  tuition: number | undefined,
  min?: number,
  max?: number
) => {
  if (tuition == null) return false; 
  if (min != null && tuition < min) return false;
  if (max != null && tuition > max) return false;
  return true;
};


const matchProgramName = (programName: string | undefined, filter: string) => {
  if (!filter || filter === "all") return true;
  return (programName || "").toLowerCase().includes(filter.toLowerCase());
};

const selectAllResults = (state: RootState) => state.search.searchResults;
export const selectActiveFilters = (state: RootState) => state.search.activeFilters;

export const selectVisiblePrograms = createSelector(
  [selectAllResults, selectActiveFilters],
  (programs, filters) => {
    const { level, field, city, language, tuitionMin, tuitionMax } = filters;
    return (programs || []).filter((p: Program) => {
      const levelOk = matchesCategory((p.level as any) || "", level);
      const fieldOk = matchProgramName((p.name as any) || "", field);
      const cityOk = matchesCategory((p.city as any) || "", city);
      const langOk = matchesLanguage(p.language as any, language);
      const tuitionOk = matchesTuition(
        p.tuition as any,
        tuitionMin,
        tuitionMax
      );
      return levelOk && fieldOk && cityOk && langOk && tuitionOk;
    });
  }
);

export const selectVisibleCount = createSelector(
  [selectVisiblePrograms],
  (list) => list.length
);