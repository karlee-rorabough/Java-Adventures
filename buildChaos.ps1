# Compile the program #
javac -d bin -sourcepath . -classpath . Chaos.java 
# Run the program #
java -classpath bin org.frc.teams.team1759.Chaos.Chaos 
# Convert the ppm file to a png file #
magick convert foo.ppm foo.png 
