const UserMenu = () => {
  return (
    <div className="absolute right-0 mt-2 w-56 bg-white border border-gray-200 rounded-xl shadow-lg z-50">
      <div className="p-4 border-b border-gray-100">
        <p className="text-sm font-medium text-gray-900">John Doe</p>
        <p className="text-xs text-gray-500">student@example.com</p>
      </div>
      <div className="p-2">
        <a
          href="#"
          className="block px-4 py-2 text-sm text-gray-700 hover:text-blue-600 hover:bg-blue-50 rounded-lg">
          My Applications
        </a>
        <a
          href="#"
          className="block px-4 py-2 text-sm text-gray-700 hover:text-blue-600 hover:bg-blue-50 rounded-lg">
          Saved Programs
        </a>
        <a
          href="#"
          className="block px-4 py-2 text-sm text-gray-700 hover:text-blue-600 hover:bg-blue-50 rounded-lg">
          Profile Settings
        </a>
        <hr className="my-2 border-gray-200" />
        <a
          href="#"
          className="block px-4 py-2 text-sm text-gray-700 hover:text-red-600 hover:bg-red-50 rounded-lg">
          Sign in
        </a>
      </div>
    </div>
  );
};
export default UserMenu;
