package com.shemhazaicraft.api.server.status;



import java.time.Instant;


public interface MinecraftStatusClient {

 public ServerStatus query(String ip, int port);

}
