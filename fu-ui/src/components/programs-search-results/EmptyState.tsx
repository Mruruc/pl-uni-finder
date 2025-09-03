import { Search } from "lucide-react";

interface EmptyStateProps {
  onClear: () => void;
}

const EmptyState = ({ onClear }: EmptyStateProps) => (
  <div className="text-center py-12">
    <div className="text-gray-400 mb-4">
      <Search className="w-16 h-16 mx-auto" />
    </div>
    <h3 className="text-xl font-medium text-gray-900 mb-2">
      No programs found
    </h3>
    <p className="text-gray-600 mb-6">
      Try adjusting your search terms or filters to find what you're looking
      for.
    </p>
    <button
      onClick={onClear}
      className="px-6 py-3 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-lg transition-colors">
      Clear Search
    </button>
  </div>
);

export default EmptyState;
