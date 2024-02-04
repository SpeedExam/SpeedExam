import { INavbarData } from "./helper";

export const logout: INavbarData = {
  routeLink: '/logout',
  icon: 'logout',
  label: 'Logout',
};

export const navbarData: INavbarData[] = [
    {
        routeLink: '/admin/dashboard',
        icon: 'dashboard',
        label: 'Dashboard',
    },
    {
        routeLink: '/admin/users',
        icon: 'people',
        label: 'students'
    },
    {
        routeLink: '/admin/settings',
        icon: 'settings',
        label: 'Settings',
    },
];
