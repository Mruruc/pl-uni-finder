import { Search, X } from "lucide-react";
import { useState } from "react";
import { useNavigate } from "react-router";
import SearchSuggestions from "./SearchSuggestions";

const PROGRAMS_PATH = "/programs";

const SearchBar = () => {
  const navigate = useNavigate();
  const [searchQuery, setSearchQuery] = useState("");

  const [isFocused, setIsFocused] = useState(false);

  const handleSubmit = (event?: React.FormEvent<HTMLFormElement>) => {
    event?.preventDefault();
    if (!searchQuery) return;
    navigate(`${PROGRAMS_PATH}?q=${encodeURIComponent(searchQuery.trim())}`);
  };

  const handleSearchSuggestion = (suggestion: string) => {
    setSearchQuery(suggestion);
    navigate(`${PROGRAMS_PATH}?q=${encodeURIComponent(suggestion.trim())}`);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      event.preventDefault();
      event.stopPropagation();
      handleSubmit();
    }
  };

  return (
    <form onSubmit={handleSubmit} className="relative group">
      {/* Search Icon */}
      <button
        type="button"
        onClick={() => handleSubmit()}
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
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        onKeyDown={handleKeyDown}
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
        className={`w-full pl-14 pr-12 py-4 text-base bg-white border-2 rounded-2xl transition-all duration-300 shadow-lg hover:shadow-xl ${
          isFocused
            ? "border-blue-500 ring-4 ring-blue-100 shadow-xl"
            : "border-gray-200 hover:border-gray-300"
        } focus:outline-none placeholder:text-gray-400 text-gray-700`}
      />

      {/* Clear Button */}
      {searchQuery && (
        <button
          type="button"
          onClick={() => setSearchQuery("")}
          className="absolute right-5 top-1/2 -translate-y-1/2 p-1 rounded-full hover:bg-gray-100 transition-colors duration-200 group"
          aria-label="Clear search">
          <X className="w-4 h-4 text-gray-400 group-hover:text-gray-600" />
        </button>
      )}

      {/* Search Suggestions Dropdown */}
      {isFocused && <SearchSuggestions onSelect={handleSearchSuggestion} />}
    </form>
  );
};
export default SearchBar;
