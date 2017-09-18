
start = 0x433121  # hex literal, gives us a regular integer
end = 0x43317e
p = 0

for i in xrange(start, end + 1):
    print 'backgroundScannerSearchStrings['+str(p)+']=' + '\"'+format(i, 'x')+'\"'+';'+' // Session Starting in 1min'
    p=p+1

print 'Overall Search Strings'
print str(p)







start = 0x433121  # hex literal, gives us a regular integer
end = 0x43317e
p = 0

for i in xrange(start, end + 1):
    print 'backgroundScannerDisplayStrings['+str(p)+']=\"Next Session Starting in 1min\"; // Session Starting in 1min'
    p=p+1
    
print 'Overall Display Strings'
print str(p)

