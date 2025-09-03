import { Outlet, ScrollRestoration } from "react-router";
import Footer from "../components/common/Footer";

const StudentLayout = () => {
  return (
    <div>
      <ScrollRestoration />
      <Outlet />
      <Footer />
    </div>
  );
};
export default StudentLayout;
