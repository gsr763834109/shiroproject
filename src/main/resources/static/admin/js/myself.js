layui.config({
    base : "assets/extends/"
}).use(['jquery', 'layer', 'navTree'],function(){
    var layer = layui.layer,
        navTree = layui.navTree,
        $ = layui.jquery;	//使用 layui 内部的 jQuery

    //渲染导航菜单（出现左侧垂直导航）
    var requestNavUrl = "";	//请求导航菜单数据的接口地址（这里 json 是相对于 index.html 的路径）
    navTree.render({
        elem: '#sideNav',	//注意框架布局中应存在你指定的垂直导航容器
        headerNavElem: false,	//头部水平导航将联动左侧垂直导航，如果不需要渲染头部水平导航，请设置为 false
        url: requestNavUrl, //获取菜单json地址
        recursion: false,	//已是树形结构，不需要递归
        rootValue: null,	//根节点主键值
        home: "page/home.html",	//默认或缺省时打开的页面链接
        currentNavKey: "current_nav",
        done: function(){
            //菜单渲染完成后的回调方法
            alert("后台菜单渲染完成！");
        }
    });
});