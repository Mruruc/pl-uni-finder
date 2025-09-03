interface LoadingStateProps {
  message?: string;
}

const LoadingState = ({ message = "Loading ..." }: LoadingStateProps) => (
  <div className="flex items-center justify-center py-12">
    <div className="animate-spin rounded-full h-12 w-12 border-b-4 border-blue-600"></div>
    <span className="ml-3 text-gray-600">{message}</span>
  </div>
);

export default LoadingState;
