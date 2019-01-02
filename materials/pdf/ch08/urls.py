
from django.contrib import admin
from django.urls import path,include
from . import  views
urlpatterns = [

    path('login/', views.do_Login),
    path('pagelist/',views.getPageList),
    path('getpagecontent/<int:blog_id>',views.getBlogDetail,name="bid"),
    path('editpage/<int:blog_id>',views.getEditPage,name="edit_page"),

    path('doSubmit/',views.doSubmit,name="insert_action")


]
