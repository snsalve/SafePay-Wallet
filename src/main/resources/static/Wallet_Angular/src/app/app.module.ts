import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';;
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';;
import { HomepageComponent } from './component/homepage/homepage.component';;
import { NewUserComponent } from './component/new-user/new-user.component';;
import { SignInComponent } from './component/sign-in/sign-in.component';
import { SignInHomeComponent } from './component/sign-in-home/sign-in-home.component';
import { AccDetailsComponent } from './component/acc-details/acc-details.component';;
import { DepositComponent } from './component/deposit/deposit.component';
import { WithdrawComponent } from './component/withdraw/withdraw.component';
import { TransferComponent } from './component/transfer/transfer.component';
import { TransactionsComponent } from './component/transactions/transactions.component';
import { ShowBalanceComponent } from './component/show-balance/show-balance.component';
import { LogoutComponent } from './component/logout/logout.component';
import { UpdateComponent } from './component/update/update.component';
@NgModule({
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [
        AppComponent,
        HomepageComponent,
        NewUserComponent,
        SignInComponent,
        DepositComponent,
        WithdrawComponent,
        TransactionsComponent,
        TransferComponent,
        ShowBalanceComponent,
        SignInHomeComponent,
        AccDetailsComponent,
        LogoutComponent,
        UpdateComponent
        ],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule { }