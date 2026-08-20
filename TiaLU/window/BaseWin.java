package TiaLU.window;

/*
 * 这是一个窗口的基础类你可以继承并修改
 * 他包含的不多但足够自由
 * A flexible base window class. Extend and modify as needed.
 * You should know that I used AI or a translation website to translate this.
 */

public abstract class BaseWin {

    protected int Width, Height;
    protected String Title;

    public BaseWin(int width, int height, String title) {
        Width = width;
        Height = height;
        Title = title;
    }

    public void Start() {
        Create();       // 1_创建窗口
        Init();         // 2_初始化参数
        ThreadStart();  // 3_启动线程
        Run();          // 4_主循环
        Stop();         // 5_主循环结束
    }

    public abstract void Init();            //初始化
    public abstract void Create();          //创建窗口
    public abstract void Stop();            //停止窗口
    public abstract void Run();             //主循环
    public abstract void ThreadStart();     //多线程启动

    //创建和初始化分开是因为我觉得可能有人不小心先初始化然后创建
    //The creation and initialization are separated because I think someone might accidentally initialize first and then create.

    public String GetTitle() { return Title; }

    public void SetTitle(String title) { Title = title; }

    public int GetWidth() { return Width; }

    public void SetWidth(int width) { Width = width; }

    public int GetHeight() { return Height; }

    public void SetHeight(int height) { Height = height; }

    //自带工具函数 Built-in utility functions PS:void, haha
}