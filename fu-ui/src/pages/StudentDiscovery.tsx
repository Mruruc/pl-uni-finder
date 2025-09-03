import Header from "../components/common/Header";
import NavigationBar from "../components/common/NavigationBar";
import HeroSection from "../components/discovery/HeroSection";

const StudentDiscovery = () => {
  return (
    <div className="min-h-screen bg-gray-50/50 font-sans">
      <Header centerSection={<NavigationBar />} />

      <main className="max-w-screen-2xl mx-auto px-4 sm:px-6 lg:px-8 py-10">
        {/* Hero Section with Search */}
        <HeroSection />
      </main>
    </div>
  );
};

export default StudentDiscovery;
