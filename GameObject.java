public abstract class GameObject implements Drawable {
  
  //---------------------------------------------------Properties
  private int x, y, width, height;
  
  //---------------------------------------------------Getters and Setters
  public void setX(int x){
   this.x = x; 
  }
  
  public int getX(){
    return this.x;
  }
  
  public void setY(int y){
   this.y = y; 
  }
  
  public int getY(){
    return this.y;
  }
  
  //Width is always non negative
  public void setWidth(int width){
   if(width <= 0) this.width = 0; 
    else this.width = width;
  }
  
  public int getWidth(){
    return this.width;
  }
  
  //Height is always non negative
  public void setHeight(int height){
   if(height <= 0) this.height = 0;
    else this.height = height;
  }
  
  public int getHeight(){
    return this.height;
  }
