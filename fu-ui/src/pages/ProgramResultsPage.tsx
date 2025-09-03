import Header from "../components/common/Header.tsx";
import LoadingState from "../components/common/LoadingState.tsx";
import CompactSearchBar from "../components/programs-search-results/CompactSearchBar.tsx";
import EmptyState from "../components/programs-search-results/EmptyState.tsx";
import FiltersSidebar from "../components/programs-search-results/filters/FiltersSidebar.tsx";
import LoadMoreSection from "../components/programs-search-results/LoadMoreSection.tsx";
import ProgramList from "../components/programs-search-results/ProgramList.tsx";
import ResultsHeader from "../components/programs-search-results/ResultsHeader.tsx";
import { useVisibleCount, useVisiblePrograms } from "../hooks/hooks.ts";
import useProgramsSearch from "../hooks/useProgramsSearch.ts";

const ProgramsSearchResultsPage = () => {
  const {
    query,
    urlSearch,
    status,
    searchResults,
    resultCount,
    handleChange,
    handleSubmit,
    handleClear,
    handleSuggestionSelect,
  } = useProgramsSearch();

  const programs = useVisiblePrograms();
  const total = useVisibleCount();


  return (
    <div className="min-h-screen bg-gray-50/50">
      <Header
        centerSection={
          <CompactSearchBar
            searchQuery={query}
            onSearchChange={handleChange}
            onSearchSubmit={handleSubmit}
            onSearchClear={() => handleClear()}
            onSuggestionSelect={handleSuggestionSelect}
          />
        }
      />

      <main className="max-w-screen-2xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
          <FiltersSidebar />

          <div className="lg:col-span-3">
            <ResultsHeader
              query={urlSearch}
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
                    <ProgramList programs={programs} />
                    <LoadMoreSection
                      shownCount={total}
                      totalCount={total}
                    />
                  </>
                ) : (
                  <EmptyState onClear={() => handleClear(true)} />
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
