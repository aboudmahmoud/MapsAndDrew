package com.example.byawatjetpcompest.UtilModle

sealed class dataStus<T>(val data:T?=null,val Messeg:String?=null)
{
    class  succses<T>( data:T): dataStus<T>(data)
    class  error<T>( Messeg:String?,data:T?=null): dataStus<T>(data,Messeg)
    class  Loading<T>: dataStus<T>()
}
