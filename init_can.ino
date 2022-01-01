void init_can(){
  
  mcp2515battery.reset();
  mcp2515battery.setBitrate(CAN_250KBPS, MCP_8MHZ);
  mcp2515battery.setNormalMode();

  mcp2515controllerLeft.reset();
  mcp2515controllerLeft.setBitrate(CAN_250KBPS, MCP_8MHZ);
  mcp2515controllerLeft.setNormalMode();

  mcp2515controllerRight.reset();
  mcp2515controllerRight.setBitrate(CAN_250KBPS, MCP_8MHZ);
  mcp2515controllerRight.setNormalMode();
  
}
