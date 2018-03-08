# Generate recolored orb images

# It would be nice if we could extract the originals from the jar, but for now, manually copy them to the red subfolder
# Extract from jar file
#jarfile=../../../../../../../../ModTheSpire/desktop-1.0.jar

# from colorNotes:
# 110 Hue
# -34 Saturation
# -8 Lightness
# We do halve of that
# 
# For modulate this becomes:

use strict;
for my $infile (glob "red/*.png") {
  my $outfile = $infile;
  $outfile =~ s{red/}{};
  #$infile =~ m{\d+} or die "no layer nr";
  my $step = 0+$&;
  my $fraction = 0.5; #($step+2)/10;
  my $lightness  = 100-8*$fraction*0;
  my $saturation = 100-34*$fraction*0;
  my $hue        = 100+100*(110/180)*$fraction;
  print("$infile -> $outfile     ");
  print("$step/6=$fraction     ");
  print "Modulate: $lightness,$saturation,$hue\n";
  `convert $infile -modulate $lightness,$saturation,$hue $outfile`;
}
