<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.2" tiledversion="1.2.4" name="blockTileSet" tilewidth="34" tileheight="34" tilecount="12" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="0">
  <image width="34" height="34" source="../img/blocks/redBlock.png"/>
 </tile>
 <tile id="1">
  <image width="34" height="34" source="../img/blocks/blueBlock.png"/>
 </tile>
 <tile id="2">
  <image width="34" height="34" source="../img/blocks/greenBlock.png"/>
 </tile>
 <tile id="6">
  <image width="34" height="34" source="../img/blocks/wallBlocks.png"/>
 </tile>
 <tile id="7">
  <image width="34" height="34" source="../img/blocks/floorBlocks.png"/>
 </tile>
 <tile id="9">
  <properties>
   <property name="DPTolerance" type="int" value="3"/>
  </properties>
  <image width="34" height="34" source="../img/blocks/yelloBlock.png"/>
 </tile>
 <tile id="10">
  <properties>
   <property name="BlockType" value="Death"/>
  </properties>
  <image width="34" height="34" source="../img/blocks/deathBlock.png"/>
 </tile>
 <tile id="11">
  <properties>
   <property name="BlockType" value="Stone"/>
  </properties>
  <image width="34" height="34" source="../img/blocks/stoneBlocks.png"/>
 </tile>
 <tile id="12">
  <properties>
   <property name="BlockType" value="SemiDeath"/>
   <property name="PlayerHP" type="int" value="2"/>
  </properties>
  <image width="34" height="34" source="../img/blocks/oneLifeBlock.png"/>
 </tile>
 <tile id="14">
  <properties>
   <property name="BlockType" value="Explosion"/>
   <property name="NumOfAffectedBlocks" type="int" value="10"/>
  </properties>
  <image width="34" height="34" source="../img/blocks/randomBlock.png"/>
 </tile>
 <tile id="16">
  <image width="34" height="34" source="../img/blocks/orangeBlock.png"/>
 </tile>
 <tile id="18">
  <image width="34" height="34" source="../img/blocks/whiteBlock.png"/>
 </tile>
</tileset>
