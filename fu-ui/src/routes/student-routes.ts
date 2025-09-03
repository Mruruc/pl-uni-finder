import type { RouteObject } from "react-router";
import StudentLayout from "../layouts/StudentLayout";
import ProgramDetailsPage from "../pages/ProgramDetailsPage";
import ProgramsSearchResultsPage from "../pages/ProgramResultsPage.tsx";
import StudentDiscovery from "../pages/StudentDiscovery";

const routes: RouteObject[] = [
  {
    path: "/",
    Component: StudentLayout,
    children: [
      {
        index: true,
        Component: StudentDiscovery,
      },
      {
        path: "/programs",
        Component: ProgramsSearchResultsPage,
      },
      {
        path: "/programs/:id",
        Component: ProgramDetailsPage,
      },
    ],
  },
];

const getRoutes = (): RouteObject[] => {
  return routes;
};

export default getRoutes;
