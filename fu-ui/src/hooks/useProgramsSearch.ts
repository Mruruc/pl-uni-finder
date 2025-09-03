import { useEffect, useRef } from "react";
import {
  clearSearchQuery,
  setSearchQuery,
} from "../features/search/searchSlice";
import { fetchProgramsThunk } from "../features/search/searchThunk";
import { useAppDispatch, useAppSelector } from "./hooks";
import useDebounce from "./useDebounce";
import { useSearchParams } from "react-router";

const useProgramsSearch = () => {
  const dispatch = useAppDispatch();

  const { query, status, searchResults, resultCount } = useAppSelector(
    (state) => state.search
  );

  const [searchParams, setSearchParams] = useSearchParams();
  const urlSearch = (searchParams.get("search") || "").trim();

  const debouncedSearch = useDebounce(urlSearch, 350);

  useEffect(() => {
    if (query !== urlSearch) {
      dispatch(setSearchQuery(urlSearch));
    }
  }, [urlSearch, query, dispatch]);

  const lastFetchKeyRef = useRef<string>("");

  useEffect(() => {
    if (!debouncedSearch) return;

    const key = debouncedSearch;
    if (key === lastFetchKeyRef.current) return;
    lastFetchKeyRef.current = key;

    dispatch(fetchProgramsThunk({ query: debouncedSearch }));
  }, [debouncedSearch, dispatch]);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = event.target.value;
    const currentValue = searchParams.get("search") || "";

    if (newValue !== currentValue) {
      const sp = new URLSearchParams(searchParams);
      newValue.trim() ? sp.set("search", newValue) : sp.delete("search");
      setSearchParams(sp);
    }

    if (newValue !== query) {
      dispatch(setSearchQuery(newValue));
    }
  };

  const handleSubmit = () => {
    const searchTerm = query.trim();
    if (!searchTerm) return;
    const currentParam = searchParams.get("search") || "";
    if (searchTerm !== currentParam) {
      setSearchParams({ search: searchTerm });
    }
  };

  const handleClear = (fetchAll = false) => {
    const sp = new URLSearchParams(searchParams);
    sp.delete("search");

    if (fetchAll) {
      sp.set("search", "all");
    }

    setSearchParams(sp);
    dispatch(fetchAll ? setSearchQuery("all") : clearSearchQuery());
  };

  const handleSuggestionSelect = (term: string) => {
    const sp = new URLSearchParams(searchParams);
    sp.set("search", term);
    setSearchParams(sp);
    dispatch(setSearchQuery(term));
  };

  return {
    query,
    urlSearch,
    status,
    searchResults,
    resultCount,
    handleChange,
    handleSubmit,
    handleClear,
    handleSuggestionSelect,
  };
};

export default useProgramsSearch;
