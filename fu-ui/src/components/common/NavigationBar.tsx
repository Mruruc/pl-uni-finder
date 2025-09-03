import { ChevronDown } from "lucide-react";
import { useMemo } from "react";
import { useNavigate } from "react-router";
import { setFilter } from "../../features/search/searchSlice";
import { useAppDispatch } from "../../hooks/hooks";
import type { SearchFilters } from "../../types/filter.types";

const NavigationBar = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { navigationItems, programsDropdownItems } = useMemo(
    () => ({
      navigationItems: [
        { name: "Programs", hasDropdown: true },
        { name: "Universities" },
        { name: "Scholarships" },
        { name: "About" },
        { name: "Contact" },
      ],
      programsDropdownItems: [
        { name: "Bachelor's Programs", level: "bachelor" as const },
        { name: "Master's Programs", level: "master" as const },
        { name: "PhD Programs", level: "phd" as const },
        { name: "Popular Fields", level: "all" as const },
      ],
    }),
    []
  );

  const handleFilterChange = <K extends keyof SearchFilters>(
    key: K,
    value: SearchFilters[K]
  ) => {
    dispatch(setFilter({ key, value }));
    navigate("/programs?search=all");
  };

  return (
    <div className="hidden md:block">
      <div className="ml-10 flex items-baseline space-x-1">
        {navigationItems.map((item) => (
          <div key={item.name} className="relative group">
            <button className="flex items-center px-4 py-2 text-sm font-medium text-gray-700 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-all duration-200">
              {item.name}
              {item.hasDropdown && (
                <ChevronDown className="ml-1 w-4 h-4 group-hover:rotate-180 transition-transform duration-200" />
              )}
            </button>
            {item.hasDropdown && (
              <div className="absolute top-full left-0 mt-1 w-56 bg-white border border-gray-200 rounded-xl shadow-lg opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200 z-50">
                <div className="p-2">
                  {programsDropdownItems.map((program) => (
                    <div
                      onClick={() => handleFilterChange("level", program.level)}
                      key={program.name}
                      className="block px-4 py-2 text-sm text-gray-700 hover:text-blue-600 hover:bg-blue-50 rounded-lg">
                      {program.name}
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};
export default NavigationBar;
