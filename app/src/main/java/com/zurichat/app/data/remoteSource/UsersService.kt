package com.zurichat.app.data.remoteSource

import com.zurichat.app.models.*
import com.zurichat.app.models.network_response.OrganizationMembers
import com.zurichat.app.models.organization_model.*
import com.zurichat.app.ui.login.password.confirm.ConfirmPasswordData
import com.zurichat.app.ui.login.password.confirm.ConfirmResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


//'https://api.zuri.chat/v1/auth/login'

interface UsersService {

    @POST("auth/login")
    suspend fun login(@Body loginBody: LoginBody): LoginResponse

    @POST("account/request-password-reset-code")
    suspend fun passwordreset(@Body passwordResetBody: PasswordResetBody): PasswordRestReponse

    @POST("organizations")
    suspend fun createOrganization(@Body organizationCreator: OrganizationCreator): OrganizationCreatorResponse

    @PATCH("organizations/{organization_id}/name")
    suspend fun updateOrganizationName(
        @Path("organization_id") orgId: String,
        @Body organization_name: OrganizationName
    ): OrganizationNameResponse

    @POST("users")
    fun register(@Body registerUser: RegisterUser?): Call<RegisterUser?>?


    @POST("account/verify-account")
    fun verifyEmail(@Body verifyEmail: VerifyEmail?): Call<VerifyEmail?>?

    @GET("organizations/{organization_id}/members")
    suspend fun getMembers(@Header("Authorization") token: String, @Path("organization_id") orgId: String): Response<UserList>

    @POST("account/request-password-reset-code")
    suspend fun passwordReset(@Body passwordResetBody: PasswordResetBody): Response<PasswordRestReponse>

    @GET("organizations/{organization_id}/members")
    suspend fun getMembers(@Path("organization_id") org_id: String): Response<OrganizationMembers>

    @GET("organizations/{organization_id}/members/{member_id}")
    suspend fun getMember(@Path("organization_id") org_id: String, @Path("member_id") member_id: String): Response<OrganizationMember>

    @GET("organizations/{organization_id}/members")
    suspend fun getMemberByEmail(
        @Path("organization_id") org_id: String,
        @Query("query") email: String
    ): Response<OrganizationMembers>

    @POST("auth/logout")
    suspend fun logout(@Body logoutBody: LogoutBody): Response<LogoutResponse>

    @POST("account/verify-reset-password")
    suspend fun verifyResetOtp(@Body resetCodeBody: ResetCodeBody): Response<ResetCodeResponse>

    @POST("account/update-password/{verification_code}")
    suspend fun updatePass(
        @Path("verification_code") verCode: String,
        @Body updatePassBody: UpdatePassBody
    ): Response<LogoutResponse>

    @POST("auth/confirm-password")
    suspend fun confirmpassword(@Body confirmpassword: ConfirmPasswordData): ConfirmResponse
}


