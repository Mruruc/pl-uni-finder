export type Level = "all" | "bachelor" | "master" | "phd";
export type Language = "any" | "english" | "polish" | "both"; 

export interface SearchFilters {
  level: Level;
  field: string;
  city: string;
  language: Language;
  tuitionMin?: number;
  tuitionMax?: number;
}
