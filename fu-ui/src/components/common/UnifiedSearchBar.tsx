import { Search, X } from "lucide-react";
import { useEffect, useState } from "react";
import { setSearchQuery } from "../../features/search/searchSlice";
import { useAppDispatch, useAppSelector } from "../../hooks/hooks";
import useDebounce from "../../hooks/useDebounce";
import SearchSuggestions from "../discovery/SearchSuggestions";

interface SearchBarProps {
  variant?: "hero" | "compact";
  onSearch?: () => void;
  autoSearch?: boolean;
}

const UnifiedSearchBar = ({
  variant = "hero",
  onSearch,
  autoSearch,
}: SearchBarProps) => {
  const dispatch = useAppDispatch();
  const query = useAppSelector((state) => state.search.query);

  const [isFocused, setIsFocused] = useState(false);
  const [inputValue, setInputValue] = useState(query);

  const debouncedQuery = useDebounce(inputValue, 400);

  useEffect(() => {
    dispatch(setSearchQuery(debouncedQuery));
  }, [debouncedQuery, dispatch]);

  const handleQueryChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      event.preventDefault();
      onSearch?.();
    }
  };

  const handleSearchSelect = (selected: string) => {
    setInputValue(selected);
    dispatch(setSearchQuery(selected));
    onSearch?.();
  };

  const handleClear = () => {
    setInputValue("");
    dispatch(setSearchQuery(""));
  };

  return (
    <div
      className={`relative ${
        variant === "hero" ? "group max-w-2xl mx-auto" : "flex-1 max-w-2xl mx-6"
      }`}>
      <button
        type="button"
        onClick={onSearch}
        aria-label="Submit search"
        className="absolute left-5 top-1/2 -translate-y-1/2 z-10">
        <Search
          className={`w-5 h-5 transition-colors duration-200 ${
            isFocused ? "text-blue-500" : "text-gray-400"
          }`}
        />
      </button>

      {/* Search Input */}
      <input
        type="text"
        placeholder="Search programs, universities, or fields of study..."
        value={inputValue}
        onChange={handleQueryChange}
        onKeyDown={!autoSearch ? handleKeyDown : undefined}
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
        className={`w-full pl-14 pr-12 py-${
          variant === "hero" ? "4" : "3"
        } text-base bg-white border-2 rounded-2xl transition-all duration-300 shadow-lg hover:shadow-xl ${
          isFocused
            ? "border-blue-500 ring-4 ring-blue-100 shadow-xl"
            : "border-gray-200 hover:border-gray-300"
        } focus:outline-none placeholder:text-gray-400 text-gray-700`}
      />

      {/* Clear Button */}
      {query && (
        <button
          type="button"
          onClick={handleClear}
          className="absolute right-5 top-1/2 -translate-y-1/2 p-1 rounded-full hover:bg-gray-100 transition-colors duration-200 group"
          aria-label="Clear search">
          <X className="w-4 h-4 text-gray-400 group-hover:text-gray-600" />
        </button>
      )}

      {/* Search Suggestions Dropdown */}
      {isFocused && <SearchSuggestions onSelect={handleSearchSelect} />}
    </div>
  );
};
export default UnifiedSearchBar;
