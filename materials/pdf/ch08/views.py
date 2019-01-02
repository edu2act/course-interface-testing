from django.shortcuts import render
from django.http import  HttpResponse
from api.models import Blog

# Create your views here.
def do_Login(request):
    return HttpResponse("hello");

def getPageList(request):
    blogs = Blog.objects.all()
    return render(request, "mainpage.html", {"parablogs": blogs})

def getBlogDetail(request,blog_id):
    blog=Blog.objects.get(id=blog_id)
    return render(request,"blogdetail.html",{"parablog":blog})
def getEditPage(request,blog_id):
    if(str(blog_id)=='0'):
        return  render(request,"editblog.html")
    else:
        blog = Blog.objects.get(id=blog_id)
        return render(request, "editblog.html",{"parablog":blog})



def doSubmit(request):
    t = request.POST.get('title', '')
    c = request.POST.get('content', 'Content')
    b_id=request.POST.get('blog_id','0')
    print(b_id)
    if(str(b_id)=='0'):
          # 向数据库添加记录
        blog = Blog.objects.create(title=t, content=c)

    else:
        blog=Blog.objects.get(id=b_id)
        blog.title=t
        blog.content=c
        blog.save()
    return render(request, "blogdetail.html", {"parablog": blog})
