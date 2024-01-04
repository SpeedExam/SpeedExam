import {CanActivateFn, Router} from '@angular/router';
import {AuthServiceService} from "../services/auth-service.service";

export const authGuardGuard: CanActivateFn = (route, state) => {
  const auth=new AuthServiceService();
  const router = new Router();
  if (auth.isLogged()) {
    return true;
  } else {
    router.navigate(['login']);
    return false;
  }
};
