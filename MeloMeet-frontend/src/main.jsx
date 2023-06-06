import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Register from './pages/Register.jsx';
import Login from './pages/Login.jsx';
import NewVenue from './pages/NewVenue.jsx';
import NewEvent from './pages/NewEvent.jsx';
import NewGroup from './pages/NewGroup.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      {
        path: '/register',
        element: <Register />
      },
      {
        path: '/login',
        element: <Login />
      },
      {
        path: '/venue',
        children: [
          {
            path: '/venue/add',
            element: <NewVenue />
          }
        ]
      },
      {
        path: '/event',
        children: [
          {
            path: '/event/add',
            element: <NewEvent />
          }
        ]
      },
      {
        path: '/group',
        children: [
          {
            path: '/group/add',
            element: <NewGroup />
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
