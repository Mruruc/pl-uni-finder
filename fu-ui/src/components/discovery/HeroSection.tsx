import QuickActions from "./QuickActions";
import SearchBar from "./SearchBar";

const HeroSection = () => {
  return (
    <div className="py-12 text-center">
      {/* Title + Subtitle */}
      <div className="mb-8">
        <h2 className="text-4xl sm:text-5xl font-bold text-gray-900 tracking-tight mb-4 bg-gradient-to-r from-gray-900 via-blue-900 to-gray-900 bg-clip-text text-transparent">
          Find Your Perfect Program
        </h2>
        <p className="text-lg sm:text-xl text-gray-600 max-w-3xl mx-auto leading-relaxed">
          Explore top-tier Bachelor's and Master's degrees at leading
          universities in Poland.
        </p>
      </div>

      {/* Search */}
      <div className="max-w-2xl mx-auto">
        <SearchBar />
        {/* <UnifiedSearchBar variant="hero" onSearch={() => { navigate(PROGRAMS_PATH); }} /> */}

        {/* Search Stats */}
        <div className="mt-4 text-sm text-gray-500">
          <span>✨ Over 500+ programs available • Updated daily</span>
        </div>

        {/* Quick Links */}
        <QuickActions />
      </div>
    </div>
  );
};
export default HeroSection;
