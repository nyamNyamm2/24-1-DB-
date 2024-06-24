
import recipe.*;
import search.*;
import user.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame
{
    ImageIcon icon = new ImageIcon("c://app//recipe.png");
    JFrame frame;
    JMenuBar menuBar;
    JMenu userMenu, reciMenu, timeMenu;

    JMenuItem userMenu11, userMenu12;
    JMenuItem reciMenu11, reciMenu12;
    JMenuItem timeMenu11;
    JPanel jPanel;
    JButton shutdownButton;

    public Main()
    {
        frame = new JFrame("레시피 관리 프로그램");
        menuBar = new JMenuBar();
        // 메인 메뉴 항목 객체 생성
        userMenu = new JMenu("회원관리");
        reciMenu = new JMenu("레시피관리");
        timeMenu = new JMenu("검색관리");
        JLabel lb1 = new JLabel(" ", JLabel.CENTER);
        lb1.setIcon(icon);
        frame.add(lb1);
    }

    protected void startFrame() throws IOException
    {
        frame.setJMenuBar(menuBar);

        // 화원
        menuBar.add(userMenu);
        userMenu.add(userMenu11 = new JMenuItem("회원등록"));
        userMenu.add(userMenu12 = new JMenuItem("회원관리"));

        // 레시피
        menuBar.add(reciMenu);
        reciMenu.add(reciMenu11 = new JMenuItem("레시피등록"));
        reciMenu.add(reciMenu12 = new JMenuItem("레시피관리"));

        // 검색
        menuBar.add(timeMenu);
        timeMenu.add(timeMenu11 = new JMenuItem("검색조회"));

        jPanel = new JPanel();

        Container con = frame.getContentPane();
        con.add(jPanel, "North");

        frame.setLocation(600, 100);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        userMenu11.addActionListener(new UserHandler());
        userMenu12.addActionListener(new UserHandler());

        reciMenu11.addActionListener(new RecipeHandler());
        reciMenu12.addActionListener(new RecipeHandler());

        timeMenu11.addActionListener(new SearchHandler());
    }

    class UserHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (e.getSource() == userMenu11)
                {
                    new RegUserDialog("회원 등록창");
                }
                else if (e.getSource() == userMenu12)
                {
                    new SearchUserDialog("회원 조회창");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class RecipeHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (e.getSource() == reciMenu11)
                {
                    new RegRecipeDialog("레시피 등록창");
                }
                else if (e.getSource() == reciMenu12)
                {
                    new SearchRecipeDialog("레시피 조회창");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class SearchHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (e.getSource() == timeMenu11)
                {
                    new ViewSearchDialog("검색 조회 창");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        Main test = new Main();
        try
        {
            test.startFrame();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    /*
    public static void main(String[] args) throws IOException
    {
        // 지역 변수 선언
        int menu = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. 사용자 메뉴");
        System.out.println("2. 레시피 메뉴");
        System.out.println("3. 검색 메뉴");
        System.out.print("원하시는 메뉴 번호를 입력하세요: ");
        menu = Integer.parseInt(br.readLine());
        // 지역 변수 선언
        Object[][] userItems;
        UView userview = new UView();
        int userid = 0, menu = 0;
        String userName, recipeTitle, recipeDiff;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. 사용자 등록");
        System.out.println("2. 사용자 조회");
        System.out.print("원하시는 메뉴 번호를 입력하세요: ");
        menu = Integer.parseInt(br.readLine());
        if (menu == 1)
        {
            // 사용자 추가
            System.out.print("추가할 유저 이름을 입력하세요: ");
            userName = br.readLine();
            new UInsert(userName);
        } // 메뉴 1
        else if (menu == 2)
        {
            // 사용자 조회
            List<UserVO> userList = new ArrayList<UserVO>();
            userList = userview.view();

            if (userList != null && userList.size() > 0)
            {
                userItems = new String[userList.size()][2];

                for (int i = 0; i < userList.size(); i++)
                {
                    UserVO userVO = userList.get(i);
                    userItems[i][0] = Integer.toString(userVO.getUserId());
                    userItems[i][1] = userVO.getUserName();
                    System.out.print("ID: " + userVO.getUserId());
                    System.out.println(" | 이름: " + userVO.getUserName());
                } // 메뉴 2 for문
            } // 메뉴 2 if문
        } // 메뉴 2


    } // 메인메소드          */

} // Main 클래스
