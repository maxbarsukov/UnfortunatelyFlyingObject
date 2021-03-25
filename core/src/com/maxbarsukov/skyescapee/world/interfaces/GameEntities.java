package com.maxbarsukov.skyescapee.world.interfaces;

import com.maxbarsukov.skyescapee.entities.Player;
import com.maxbarsukov.skyescapee.helpers.ScrollHandler;

public interface GameEntities extends Score {
    Player getPlayer();
    ScrollHandler getScroller();
}
