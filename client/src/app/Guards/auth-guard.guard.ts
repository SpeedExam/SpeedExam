import {CanActivateFn, Router} from '@angular/router';
import {AuthServiceService} from "../services/auth-service.service";
import {NgToastService} from "ng-angular-popup";

export const authGuardGuard: CanActivateFn = (route, state) => {
  const auth = new AuthServiceService();
  const router = new Router();
  const toast = new NgToastService();
  const requiredRole = route.data && route.data['role'];

  if (auth.isLogged()) {
    const userIsProfessor = auth.getRole();

    if (requiredRole === 'professor' && userIsProfessor !="PROFESSOR") {
      // Regular user trying to access an admin route
      toast.error({detail:'Error message',summary:'Access Denied: Regular Student cannot access admin accounts.',duration:15000});
      router.navigate(['user']); // Redirect to user dashboard or any other appropriate route
      return false;
    }

    if (requiredRole === 'student' && userIsProfessor=="PROFESSOR") {
      // Admin trying to access a user route
      toast.error({detail:'Error message',summary:'Access Denied: Regular users cannot access Professor accounts.',duration:15000});
      router.navigate(['admin/dashboard']);
      return false;
    }

    return true;
  } else {
    router.navigate(['login']);
    return false;
  }
};
