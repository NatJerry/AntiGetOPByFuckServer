package natjerry;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin implements Listener {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1,Integer.MAX_VALUE,Long.MAX_VALUE, TimeUnit.DAYS,new LinkedBlockingDeque<>());
    List<String> opList = (List<String>) getConfig().getList("opList");
    List<String> BlackList = (List<String>) getConfig().getList("BlackList");
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {

    }
    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent e){
            if(e.getPlayer().isOp()){
                if(!opList.contains(e.getPlayer().getName())){
                    e.getPlayer().setOp(false);
                    e.setKickMessage(ChatColor.RED + "你非法获得了op，已被服务器永久封禁\n 若判断出先错误，请寻找服主解封");
                    String str = new String(ChatColor.RED + "你非法获得了op，已被服务器永久封禁\n 若判断出先错误，请寻找服主解封");
                    e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
                    Bukkit.getBanList(BanList.Type.NAME).addBan(e.getPlayer().getName(),str,null,null);
                }
            }
    }
    public void onPlayerChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        for(String s:BlackList){
            if(msg.indexOf(s)!=-1){

            }
        }
    }
}
