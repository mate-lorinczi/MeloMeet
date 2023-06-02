import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Register from './pages/Register.jsx';
import Login from './pages/Login.jsx';
import VenueForm from './pages/VenueForm.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      {
        path: "/register",
        element: <Register />
      },
      {
        path: "/login",
        element: <Login />
      },
      {
        path: '/venue',
        children: [
          {
            path: '/venue/add',
            element: <VenueForm />
          }
        ]
      }
    ]
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
   <RouterProvider router={router} />
  </React.StrictMode>,
)
