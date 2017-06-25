package com.example.tezya.MovieBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xietengxiao on 2017/6/5.
 */

public class MovieDatabase {
    public static String username="";
    public static  String[] MovList = new String[]{"加勒比海盗5", "李雷和韩梅梅", "神奇女侠", "从你的全世界路过", "碟仙之毕业照", "吃吃的爱"};
    public static int[] srcInfoList = new int[]{R.mipmap.pirateinfo,R.mipmap.lilei_big,R.mipmap.nvxia_big,R.mipmap.luguo_big,R.mipmap.diexian_big,R.mipmap.chichi_big};
    public static int[] srcList = new int[]{R.mipmap.pirate,R.mipmap.lilei,R.mipmap.nvxia,R.mipmap.luguo,R.mipmap.diexian,R.mipmap.chichi};
    public static int[] pricelist = new int[]{40, 30, 80, 20, 75, 60};
   public static  double[] ratelist = new double[]{5, 4.5, 4.4, 3.5, 3.0, 2.8, 2};
    public static String[] timelist= new String[]{"12:00-14:00","14:15-16:15","20:00-22:00","8:00-10:00"};
    //剧情
    public static String[] detailslist= new String[]{"剧情简介：杰克船长（约翰尼·德普饰）发现令人闻风丧胆的萨拉查船长 （哈维尔·巴登饰）竟率领着一众夺命亡灵水手逃出了百慕大三角区。他们扬言要杀尽世上所有的海盗，头号目标就是杰克船长。要想改写命运，杰克船长唯一的希望就是找到传说中海神波塞冬的三叉戟，拥有它就能拥有统治整个海洋的力量。为了寻获这件神器，杰克船长被迫和聪明美丽的天文学家卡琳娜·史密斯（卡雅·斯考达里奥饰）以及固执的年轻皇家海军亨利（布兰顿·思怀兹饰）联手出击。航行着他那破破烂烂的“黑珍珠”号，杰克船长不但决心要改变自己的厄运，同时也力求能从史上最狠毒可怕的敌人那里捡回一条命。",
            "运动全能的中学女汉子韩梅梅，各项成绩尚可，但一提起英语就头疼得要命。在结束了快乐的初中生活后，韩梅梅和魏华、林涛等一起升上了高中，同时还有李雷。如今的李雷已是脱胎换骨，从一个不起眼的小男生变成了又高又帅的男神，还有一口英伦范的纯正英语。韩梅梅也是不知不觉地喜欢上了李雷，却迟迟不敢开口，于是古灵精怪的她制定出各种作战计划，意图攻陷李雷。然而，天有不测风云，一场又一场的麻烦和变故，接二连三地降落在他们身上。多年后，韩梅梅和李雷，还能牵手吗？",
            "戴安娜·普林斯（盖尔·加朵饰）生活在亚马逊天堂岛，岛上只有女性，作为众神之王宙斯与亚马逊女王希波吕忒（康妮·尼尔森饰）的女儿，在她的成长过程中，一直受到母亲和姨母安提俄珀（罗宾·怀特饰）的悉心呵护。直到有一天，一架战机坠入天堂岛附近海域，戴安娜的平静生活由此被打破。戴安娜将坠海的飞行员史蒂夫（克里斯·派恩饰）救起，但其母亲对这位普通男人的世界没有一点兴趣。史蒂夫强调自己的目标是结束第一次世界大战，而戴安娜则认为这场人类的浩劫或许是战神阿瑞斯捣的鬼，于是决定与史蒂夫一起前往战争前线，第一次亲身体验到了人类战争的威力，并逐渐理解了身为英雄的意义和代价。",
            "陈末（邓超饰）被称为全城最贱，每天和王牌DJ小容（杜鹃饰）针锋相对，谁也不知道他们的仇恨从何而来。陈末的两个兄弟，分别是全城最傻的猪头（岳云鹏饰），全城最纯的茅十八（杨洋饰），三人每天横冲直撞，以为可以自在生活，结果都面临人生最大的转折点。陈末相遇了最神秘的幺鸡（张天爱饰），猪头打造了最惨烈的婚礼，茅十八经历了最悲伤的别离，这群人的生活一点点崩塌，往事一点点揭开。梦想，爱情，友情都离陈末远去。一个失去所有的人，已经弄丢自己的路，直到听到来自全世界的一段语音。",
            "碟仙游戏引发的诡异事件不断发生，深幽丛林惊现黑衣蒙面人，手持利器噬魂索命激情男女。邪魅女子藏身寂静校园伺机而动，学生宿舍、教室杀机重重。《碟仙之毕业照》首次将毕业校园与招魂请仙融合在一起，呈现毕业校园苦涩分别的同时融入中国传统招魂请仙大法，用青春与离奇叙写一段毕业季不为人知的秘密。",
            "想要在巨星姐姐面前证明自己的18线小演员上官娣娣，和多年期待真爱却在最后被狠狠出卖的空间站黑鸟面馆老板许春梅，当两个人的世界以想象不到的方式不期而遇，他们的命运会发生什么样的改变。"};
    public static String[][] actorlist= {{"约翰尼-德普","奥兰多-布鲁姆","杰弗里-拉什"},{"张子枫","张逸杰","成梓宁"},{"盖尔·加朵","克里斯·派恩","康妮·尼尔森"},{"邓超","白百何","杨洋"},{"陈圆","宋伟","刘俐儿"},{"徐熙娣","林志玲","金世佳"}};
    public static boolean[] all= new boolean[]{true,true,true,true,true,true};
    public static int[][]picture={{R.mipmap.lilei2,R.mipmap.jialebi2,R.mipmap.jialebi3},{R.mipmap.lilei1,R.mipmap.lilei2,R.mipmap.lilei3},{R.mipmap.lilei1,R.mipmap.lilei2,R.mipmap.lilei3},{R.mipmap.luguo1,R.mipmap.luguo2,R.mipmap.luguo3},{R.mipmap.diexian1,R.mipmap.diexian2,R.mipmap.diexian3},{R.mipmap.chichi1,R.mipmap.chichi2,R.mipmap.chichi3}};
    public static boolean[]romantic =new boolean[]{false,true,false,true,false,true};
    public static boolean[] comedy= new boolean[]{false,false,false,true,false,true};
    public static boolean[] action= new boolean[]{true,true,false,false,false,false};
    public static boolean[] horror= new boolean[]{false,false,false,false,true,false};





}
