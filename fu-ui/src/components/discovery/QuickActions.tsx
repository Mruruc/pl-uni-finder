import { useNavigate } from "react-router";

const PROGRAMS_PATH = "/programs";

const QuickActions = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-wrap justify-center gap-3 mt-6">
      <button
        onClick={() =>
          navigate(`${PROGRAMS_PATH}?search=${encodeURIComponent("all")}`)
        }
        className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white text-sm font-medium rounded-full transition-all duration-200 shadow-lg hover:shadow-xl">
        Browse All Programs
      </button>
      <button className="px-4 py-2 bg-white hover:bg-gray-50 text-gray-700 text-sm font-medium border border-gray-300 rounded-full transition-all duration-200">
        University Guide
      </button>
      <button className="px-4 py-2 bg-white hover:bg-gray-50 text-gray-700 text-sm font-medium border border-gray-300 rounded-full transition-all duration-200">
        Scholarship Info
      </button>
    </div>
  );
};
export default QuickActions;
