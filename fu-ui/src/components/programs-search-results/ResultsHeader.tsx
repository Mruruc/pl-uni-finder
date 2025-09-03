type Props = {
  query: string;
  status: string;
  resultCount: number;
};

const ResultsHeader = ({ query, status, resultCount }: Props) => {
  return (
    <div className="flex flex-col sm:flex-row items-start sm:items-center justify-between mb-6 pb-4 border-b border-gray-200">
      <div>
        <h2 className="text-2xl font-bold text-gray-900">
          {status === "loading"
            ? "Searching..."
            : `${resultCount} Programs Found`}
        </h2>
        {query && <p className="text-gray-600 mt-1">Results for "{query}"</p>}
      </div>

      {/* Sorting dropdown */}
      <div className="flex items-center gap-3 mt-4 sm:mt-0">
        <span className="text-sm font-medium text-gray-600">Sort by:</span>
        <select className="px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white min-w-[140px]">
          <option>Relevance</option>
          <option>Top Rated</option>
          <option>Tuition (Low to High)</option>
          <option>Tuition (High to Low)</option>
          <option>Newest</option>
          <option>Deadline</option>
        </select>
      </div>
    </div>
  );
};

export default ResultsHeader;
