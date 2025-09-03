import { Search, X } from "lucide-react";
import React, { useState } from "react";
import SearchSuggestions from "../discovery/SearchSuggestions";

interface CompactSearchBarProps {
  searchQuery: string;
  onSearchChange: (query: React.ChangeEvent<HTMLInputElement>) => void;
  onSearchSubmit: () => void;
  onSearchClear: () => void;
}

const CompactSearchBar = ({
  searchQuery,
  onSearchChange,
  onSearchSubmit,
  onSearchClear,
}: CompactSearchBarProps) => {
  const [isSearchFocused, setIsSearchFocused] = useState(false);

  const handleSearchSuggestionSelect = (suggestion: string) => {
    onSearchSubmit();
  };

  return (
    <div className="flex-1 max-w-2xl mx-6">
      <div className="relative">
        <button
          onClick={ onSearchSubmit}
          className="absolute left-4 top-1/2 -translate-y-1/2 p-1"
          aria-label="Submit search">
          <Search
            className={`w-5 h-5 transition-colors duration-200 ${
              isSearchFocused ? "text-blue-500" : "text-gray-400"
            }`}
          />
        </button>

        <input
          type="text"
          placeholder="Search programs, universities, or fields..."
          value={searchQuery}
          onChange={onSearchChange}
          onFocus={() => setIsSearchFocused(true)}
          onBlur={() => setIsSearchFocused(false)}
          className={`w-full pl-12 pr-12 py-3 text-base bg-white border-2 rounded-xl transition-all duration-200 ${
            isSearchFocused
              ? "border-blue-500 ring-4 ring-blue-100"
              : "border-gray-300 hover:border-gray-400"
          } focus:outline-none placeholder:text-gray-400 text-gray-700 shadow-sm`}
        />

        {searchQuery && (
          <button
            onClick={onSearchClear}
            className="absolute right-4 top-1/2 -translate-y-1/2 p-1 rounded-full hover:bg-gray-100 transition-colors duration-200"
            aria-label="Clear search">
            <X className="w-4 h-4 text-gray-400" />
          </button>
        )}
        {/* Search Suggestions Dropdown */}
        {isSearchFocused && <SearchSuggestions onSelect={onSearchSubmit} />}
      </div>
    </div>
  );
};
export default CompactSearchBar;
