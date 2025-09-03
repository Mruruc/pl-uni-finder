import { useCallback, useEffect, useState } from "react";
import { useSearchParams } from "react-router";
import {
  clearSearchQuery,
  setSearchQuery,
} from "../features/search/searchSlice";
import { fetchProgramsThunk } from "../features/search/searchThunk";
import { useAppDispatch, useAppSelector } from "./hooks";
import useDebounce from "./useDebounce";

const useSearch = () => {
  const dispatch = useAppDispatch();
  const { query, activeFilters } = useAppSelector((state) => state.search);

  // Local input (typing state)
  const [inputValue, setInputValue] = useState(query);

  // Debounce local input before syncing to Redux
  const debouncedValue = useDebounce(inputValue, 400);

  const [params] = useSearchParams();
  const queryFromUrl = params.get("q") ?? "";

  useEffect(() => {
    if (!queryFromUrl.trim()) return;

    dispatch(setSearchQuery(queryFromUrl));
    dispatch(
      fetchProgramsThunk({ query: queryFromUrl, filters: activeFilters })
    );
  }, []);

  /**
   * Auto-search when debounced value changes
   * (user paused typing)
   */
  useEffect(() => {
    if (!debouncedValue.trim()) return;



    if (query !== debouncedValue) {
      dispatch(setSearchQuery(debouncedValue));
    }

    dispatch(
      fetchProgramsThunk({ query: debouncedValue, filters: activeFilters })
    );
  }, [debouncedValue, activeFilters, dispatch]);

  /**
   * Handlers
   */
  const handleQueryChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  const handleClearSearch = (refetch = false) => {
    setInputValue("");
    dispatch(clearSearchQuery());

    if (refetch) {
      dispatch(fetchProgramsThunk({ query: "all", filters: activeFilters }));
    }
  };

  /**
   * Manual search (on Enter or search button)
   * - Commits immediately (ignores debounce)
   */
  const immediateSearch = useCallback(
    async (searchTerm: string) => {
      const finalQuery =
        typeof searchTerm === "string" ? searchTerm.trim() : inputValue.trim();

      if (!finalQuery) return;

      setInputValue(finalQuery);
      dispatch(setSearchQuery(finalQuery));

      try {
        await dispatch(
          fetchProgramsThunk({ query: finalQuery, filters: activeFilters })
        ).unwrap();
      } catch (error) {
        console.error("Search failed:", error);
      }
    },
    [query, activeFilters, dispatch]
  );

  return {
    searchQuery: inputValue,
    handleQueryChange,
    handleClearSearch,
    immediateSearch,
  };
};

export default useSearch;
