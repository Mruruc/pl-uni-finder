type Props = {
  shownCount: number;
  totalCount: number;
};

const LoadMoreSection = ({ shownCount, totalCount }: Props) => (
  <div className="text-center mt-12">
    <button className="bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-xl font-semibold transition-all shadow-md hover:shadow-lg">
      Load More Results
    </button>
    <p className="text-sm text-gray-500 mt-3">
      Showing {shownCount} of {totalCount} programs
    </p>
  </div>
);

export default LoadMoreSection;
