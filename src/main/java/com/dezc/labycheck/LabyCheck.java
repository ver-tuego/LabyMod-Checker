package com.dezc.labycheck;

import java.util.List;

import com.dezc.labycheck.events.FreezerEvent;
import com.dezc.labycheck.events.GetMessageEvent;
import com.dezc.labycheck.events.RenderEvent;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

public class LabyCheck extends LabyModAddon {

  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(new FreezerEvent());
    getApi().getEventService().registerListener(new RenderEvent());
    getApi().getEventService().registerListener(new GetMessageEvent());
  }

  @Override
  public void loadConfig() {
    RenderEvent.xCoords = getConfig().has("X") ? getConfig().get("X").getAsInt() : 10;
    RenderEvent.yCoords = getConfig().has("Y") ? getConfig().get("Y").getAsInt() : 10;
    GetMessageEvent.vkUrl = getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : "";
    FreezerEvent.enableDupeIp = getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false;
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
    list.add( new BooleanElement( "/dupeip" /* Display name */, new ControlElement.IconData( Material.GRASS ), new Consumer<Boolean>() {
      @Override
      public void accept( Boolean accepted ) {
        FreezerEvent.setEnableDupeIp(accepted);
      }
    } /* Change listener */, FreezerEvent.isEnableDupeIp() /* current value */ ) );

    StringElement vk_url = new StringElement( "VK URL" /* Display name */, new ControlElement.IconData( Material.PAPER ) /* setting's icon */,
            GetMessageEvent.getVkUrl() /* current value */, new Consumer<String>() {
      @Override
      public void accept( String accepted ) {
        GetMessageEvent.setVkUrl(accepted);
        getConfig().addProperty("vk_url", accepted);
        saveConfig();
      }
    } /* Change listener */ );

    list.add( vk_url );

    SliderElement xc = new SliderElement( "X" /* Display name */,
            new ControlElement.IconData( "labymod/textures/buttons/advanced.png" ) /* setting's icon */, RenderEvent.getxCoords() /* current value */ );

    xc.setRange( 0, 500 );
    xc.setSteps( 2 );

    xc.addCallback( new Consumer<Integer>() {
      @Override
      public void accept( Integer accepted ) {
        RenderEvent.setxCoords(accepted);
        getConfig().addProperty("X", accepted);
        saveConfig();
      }
    } );

    SliderElement yc = new SliderElement( "Y" /* Display name */,
            new ControlElement.IconData( "labymod/textures/buttons/advanced.png" ) /* setting's icon */, RenderEvent.getyCoords() /* current value */ );

    yc.setRange( 0, 600 );
    yc.setSteps( 2 );

    yc.addCallback( new Consumer<Integer>() {
      @Override
      public void accept( Integer accepted ) {
        RenderEvent.setyCoords(accepted);
        getConfig().addProperty("Y", accepted);
        saveConfig();
      }
    } );

    list.add( xc );
    list.add( yc );

  }
}