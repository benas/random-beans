/*
 * The MIT License
 *
 *   Copyright (c) 2016, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package io.github.benas.randombeans.randomizers.java8;

import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.util.Constants;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A {@link Randomizer} that generates random {@link ZonedDateTime}.
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class ZonedDateTimeRandomizer implements Randomizer<ZonedDateTime> {

    private LocalDateTimeRandomizer localDateTimeRandomizer = new LocalDateTimeRandomizer();
    
    @Override
    public ZonedDateTime getRandomValue() {
        LocalDateTime randomLocalDateTime = localDateTimeRandomizer.getRandomValue();
        ZoneId randomZoneId = getRandomZoneId();
        return ZonedDateTime.of(randomLocalDateTime, randomZoneId);
    }

    private ZoneId getRandomZoneId() {
        Set<String> availableZoneIds = ZoneOffset.getAvailableZoneIds();
        List<String> ids = new ArrayList<String>(availableZoneIds);
        return ZoneId.of(ids.get(Constants.RANDOM.nextInt(ids.size())));
    }

}