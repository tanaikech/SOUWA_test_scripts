# using "<<"

require 'benchmark'

def makearray(row)
    ar = Array.new(row){Array.new(2){}}
    (0..row-1).each{|i|
        ar[i] = [(i+1).to_s.rjust(7, "0"), "a"]
    }
    return ar
end

def division0(an, row)
    result = ""
    ecode = "\n"
    (0..row-1).each{|data|
        result << (an[data].join(',') + ecode)
    }
    return result
end

def division1(an, phi)
    string = ""
    result = ""
    ecode = "\n"
    ap = 0
    arrayp = 0
    (phi..(an.length + 1)).step(phi) do |i|
        for j in ap..i-1
            string << (an[j].join(',') + ecode)
        end
        ap = i
        result << string
        string = ""
    end
    return result
end

def division2(an, phi)
    string = ""
    ecode = "\n"
    ap = 0
    ar = Array.new(an.length / phi)
    arrayp = 0
    (phi..(an.length + 1)).step(phi) do |i|
        for j in ap..i-1
            string << (an[j].join(',') + ecode)
        end
        ap = i
        ar[arrayp] = string
        arrayp += 1
        string = ""
    end
    return ar
end

def division3(an, phi)
    string = ""
    ecode = "\n"
    ap = 0
    ar = Array.new(an.length / phi)
    arrayp = 0
    (phi..(an.length + 1)).step(phi) do |i|
        for j in ap..i-1
            string << an[j]
        end
        ap = i
        ar[arrayp] = string
        arrayp += 1
        string = ""
    end
    return ar
end

def division4(an, phi)
    string = ""
    result = ""
    ecode = "\n"
    ap = 0
    arrayp = 0
    (phi..(an.length + 1)).step(phi) do |i|
        for j in ap..i-1
            string << an[j]
        end
        ap = i
        result << string
        string = ""
    end
    return result
end


theta = 10000
omega = 3
phi = 10

row = theta
array = makearray(row)

ar1 = []
ar2 = []
result = ""

if omega == 0 then
    outdata = ""
    ptime = Benchmark.measure {
    result = division0(array, row)
    }
elsif omega == 1 then
    ptime = Benchmark.measure {
    result = division1(array, phi)
    }
elsif omega == 2 then
    ptime = Benchmark.measure {
    ar1 = division2(array, phi)
    result = division4(ar1, phi)
    }
elsif omega >= 3 then
    ptime = Benchmark.measure {
    ar1 = division2(array, phi)
    (0..omega-2).each{|e|
        ar2 = division3(ar1, phi)
        ar1 = ar2
    }
    result = division4(ar1, phi)
    }
end

puts ptime
puts "result.length = #{result.length}"
