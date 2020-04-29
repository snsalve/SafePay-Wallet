import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './component/homepage/homepage.component';
import { NewUserComponent } from './component/new-user/new-user.component';
import { SignInComponent } from './component/sign-in/sign-in.component';
import { SignInHomeComponent } from './component/sign-in-home/sign-in-home.component';
import { AccDetailsComponent } from './component/acc-details/acc-details.component';
import { ShowBalanceComponent } from './component/show-balance/show-balance.component';
import { DepositComponent } from './component/deposit/deposit.component';
import { WithdrawComponent } from './component/withdraw/withdraw.component';
import { TransferComponent } from './component/transfer/transfer.component';
import { TransactionsComponent } from './component/transactions/transactions.component';
import { LogoutComponent } from './component/logout/logout.component';
import { UpdateComponent } from './component/update/update.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'homepage', component: HomepageComponent },
  { path: 'register', component: NewUserComponent },
  { path: 'login', component: SignInComponent },
  { path: 'loginHome', component: SignInHomeComponent },
  { path: 'accDetails', component: AccDetailsComponent },
  { path: 'showBalance', component: ShowBalanceComponent },
  { path: 'deposit', component: DepositComponent },
  { path: 'withdraw', component: WithdrawComponent },
  { path: 'transfer', component: TransferComponent },
  { path: 'showTrans', component: TransactionsComponent },
  { path: 'update', component: UpdateComponent },
  { path: 'logout', component: LogoutComponent },

  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }