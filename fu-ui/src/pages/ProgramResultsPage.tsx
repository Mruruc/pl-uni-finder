import { useEffect, useRef } from "react";
import { useLocation, useSearchParams } from "react-router";
import CompactSearchBar from "../components/common/CompactSearchBar.tsx";
import Header from "../components/common/Header.tsx";
import LoadingState from "../components/common/LoadingState.tsx";
import EmptyState from "../components/programs-search-results/EmptyState.tsx";
import FiltersSidebar from "../components/programs-search-results/filters/FiltersSidebar.tsx";
import LoadMoreSection from "../components/programs-search-results/LoadMoreSection.tsx";
import ProgramList from "../components/programs-search-results/ProgramList.tsx";
import ResultsHeader from "../components/programs-search-results/ResultsHeader.tsx";
import {
  clearSearchQuery,
  setSearchQuery,
} from "../features/search/searchSlice.ts";
import { fetchProgramsThunk } from "../features/search/searchThunk.ts";
import { useAppDispatch, useAppSelector } from "../hooks/hooks.ts";
import useDebounce from "../hooks/useDebounce.ts";

const ProgramsSearchResultsPage = () => {
  const dispatch = useAppDispatch();
  const { query, status, searchResults, resultCount, activeFilters } =
    useAppSelector((state) => state.search);

  const [searchParams, setSearchParams] = useSearchParams();
  const urlQ = (searchParams.get("q") || "").trim();

  const debouncedUrlQ = useDebounce(urlQ, 800);

  useEffect(() => {
    if (query !== urlQ) dispatch(setSearchQuery(urlQ));
  }, [urlQ, query, dispatch]);

  const lastKeyRef = useRef<string>("");
  useEffect(() => {
    if (!debouncedUrlQ) return;
    const key = debouncedUrlQ;
    if (key === lastKeyRef.current) return;
    lastKeyRef.current = key;

    dispatch(
      fetchProgramsThunk({ query: debouncedUrlQ, filters: activeFilters })
    );
  }, [debouncedUrlQ, dispatch]);

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const next = e.target.value;
    const current = searchParams.get("q") || "";
    if (next !== current) {
      const sp = new URLSearchParams(searchParams);
      if (next.trim()) sp.set("q", next);
      else sp.delete("q");
      setSearchParams(sp);
    }
    if (next !== query) dispatch(setSearchQuery(next));
  };

  const handleSearchClear = () => {
    const sp = new URLSearchParams(searchParams);
    sp.delete("q");
    setSearchParams(sp);
    dispatch(clearSearchQuery());
  };

  const handleSearchSubmit = () => {
    const q = query.trim();
    if (!q) return;
    const current = searchParams.get("q") || "";
    if (q !== current) setSearchParams({ q }); 
  };

  return (
    <div className="min-h-screen bg-gray-50/50">
      <Header
        centerSection={
          <CompactSearchBar
            searchQuery={query}
            onSearchChange={handleSearchChange}
            onSearchClear={handleSearchClear}
            onSearchSubmit={handleSearchSubmit}
          />
        }
      />

      <main className="max-w-screen-2xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
          <FiltersSidebar />

          <div className="lg:col-span-3">
            <ResultsHeader
              query={query}
              status={status}
              resultCount={resultCount}
            />

            {/* Loading State */}
            {status === "loading" && (
              <LoadingState message="Searching programs..." />
            )}

            {status === "succeeded" && (
              <>
                {searchResults.length > 0 ? (
                  <>
                    <ProgramList programs={searchResults} />
                    <LoadMoreSection
                      shownCount={searchResults.length}
                      totalCount={resultCount}
                    />
                  </>
                ) : (
                  <EmptyState onClear={() => handleClearSearch(true)} />
                )}
              </>
            )}
          </div>
        </div>
      </main>
    </div>
  );
};

export default ProgramsSearchResultsPage;
