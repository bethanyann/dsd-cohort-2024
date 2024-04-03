import { Outlet } from "react-router-dom";

const Login = () => {
  return (
    <div>
      <Outlet />
      <h1>Login</h1>
    </div>
  );
};

export default Login;
