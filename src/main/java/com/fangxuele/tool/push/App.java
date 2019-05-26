package com.fangxuele.tool.push;

import com.fangxuele.tool.push.ui.Init;
import com.fangxuele.tool.push.ui.form.LoadingForm;
import com.fangxuele.tool.push.ui.form.MainWindow;
import com.fangxuele.tool.push.ui.frame.MainFrame;
import com.fangxuele.tool.push.util.ConfigUtil;
import com.fangxuele.tool.push.util.MybatisUtil;
import com.fangxuele.tool.push.util.UpgradeUtil;
import com.sun.awt.AWTUtilities;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;

/**
 * <pre>
 * Main Enter!
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2019/4/20.
 */
public class App {
    public static MainFrame mainFrame;

    public static ConfigUtil config = ConfigUtil.getInstance();

    public static SqlSession sqlSession = MybatisUtil.getSqlSession();

    public static void main(String[] args) {
        UpgradeUtil.smoothUpgrade();
        Init.initTheme();
        mainFrame = new MainFrame();
//        实验性代码
        mainFrame.setUndecorated(true);
//        mainFrame.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpacity(mainFrame,0.97f);
//        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.init();
        JPanel loadingPanel = new LoadingForm().getLoadingPanel();
        mainFrame.add(loadingPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            Init.initGlobalFont();
            Init.initAllTab();
            Init.initOthers();
            MainWindow.mainWindow.init();
            mainFrame.setContentPane(MainWindow.mainWindow.getMainPanel());
            mainFrame.addListeners();
            mainFrame.remove(loadingPanel);
        });
    }
}
