import { INavbarData } from "./helper";

export const logout: INavbarData = {
    routeLink: '/logout',
    icon: 'logout',
    label: 'Logout',
};

export const navbarData: INavbarData[] = [
    {
        routeLink: '/user',
        icon: 'school',
        label: 'Exams',
    },
    {
        routeLink: '/user/results',
        icon: 'inbox',
        label: 'Statistics'
    },
    {
        routeLink: '/user/settings',
        icon: 'settings',
        label: 'Settings',
    },
];
