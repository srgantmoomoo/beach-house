package me.srgantmoomoo.beachhouse.api.event;

import me.srgantmoomoo.beachhouse.Main;

public class Event
{
  private boolean isCancelled;

  public boolean
  isCancelled()
  {
    return isCancelled;
  }

  public void
  setCancelled(boolean cancelled)
  {
    isCancelled = cancelled;
  }
}