interface SearchSuggestionsProps {
  onSelect?: (term: string) => void;
}

// should be updated as dynamic later (from api)
const POPULAR_TERMS = [
  "Computer Science",
  "Business Administration",
  "Engineering",
  "Medicine",
  "Law",
];

const SearchSuggestions = ({ onSelect }: SearchSuggestionsProps) => {
  return (
    <div className="absolute top-full left-0 right-0 mt-2 bg-white border border-gray-200 rounded-xl shadow-2xl z-10 overflow-hidden">
      <div className="p-4 border-b border-gray-100">
        <p className="text-sm font-medium text-gray-700 mb-2">
          Popular searches
        </p>
        <div className="flex flex-wrap gap-2">
          {POPULAR_TERMS.map((term, index) => (
            <button
              key={index}
              onMouseDown={(e) => {
                e.preventDefault();
                e.stopPropagation();
              }}
              onClick={() => onSelect?.(term)}
              className="px-3 py-1.5 text-sm bg-gray-50 hover:bg-blue-50 hover:text-blue-600 text-gray-600 rounded-full transition-colors duration-200">
              {term}
            </button>
          ))}
        </div>
      </div>

      <div className="p-3">
        <p className="text-xs text-gray-500 flex items-center gap-1">
          <span>Press</span>
          <kbd className="px-1.5 py-0.5 text-xs bg-gray-100 border border-gray-300 rounded text-gray-600">
            Enter
          </kbd>
          <span>to search</span>
        </p>
      </div>
    </div>
  );
};
export default SearchSuggestions;
