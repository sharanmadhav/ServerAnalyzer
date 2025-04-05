**Server <-> DB** 
TimeSeriesURL = /[year]/[month]/[date]/[hrs]/[minutes]

->{once} [ServerUrl]/[USERKEY]/endpoint.json ~[JSONArray]
            Send all the list of url end points to the server || Get the list of end points from the server

->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/CPU.json ~[JSONObject]
            {
                ModelName:[ModelName],
                Architecture:[Architecture],
                PhysicalCores:[PhysicalCores],
                LogicalCores:[LogicalCores],
                clockSpeed:[clockSpeed],
                Vendor:[Vendor],
                CacheSize:[CacheSize]
            }

->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory.json ~[JSONObject]
            {
                TotalInstallMemory:[TotalInstallMemory],
                MemorySpeed:[MemorySpeed],
                MemoryType:[MemoryType],
                MemoryChannelCount:[MemoryChannelCount],
                InstalledModuleCount:[InstalledModuleCount]
            }
    --> {Scheduled} [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory/MemoryStats/[TimeSeriesURL].json ~[JSONArray]
            [   
                {  
                    FreeMemory:[FreeMemory],
                    UsedMemory:[UsedMemory]
                },
            ]
            
->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/Storage.json ~[JSONObject]
            {
                DiskPresent:[DiskPresent],
                DiskModel:[DiskModel],
                DiskVendor:[diskVendor],
                DiskSize:[DiskSize],
                interfaceType:[interfaceType],
                PartitionLayout:[PartitionLayout],
                readSpeed:[readSpeed],
                WriteSpeed:[WriteSpeed],
                SmartStatus:[SmartStatus]
            }

->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/GPU.json ~[JSONObject]
            {
                GPUModel:[GPUModel],
                GPUVendor:[GPUVendor],
                DriverSize:[DriverSize]
            }

->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/Network.json ~[JSONObject]
            {
                NICModel:[NICModel],
                NICModel:[NICModel],
                MacAddress:[MacAddress],
                InterfaceSpeed:[InterfaceSpeed],
                IpAddress:[IpAddress],
                InterfaceStatus:[InterfaceStatus],
            }
->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/Motherboard.json ~[JSONObject]
            {
                manufacturer:[manufacturer],
                Model:[Model],
                SerialNumber:[SerialNumber],
                BIOS/UEFIVersion:[BIOS/UEFIVersion],
                BaseBoardVersion:[BaseBoardVersion] 
            }

->{once} [ServerUrl]/[USERKEY]/[UUID]/hardware/OS.json ~[JSONObject]
            {
                OSName:[OSName],
                OSVersion:[OSVersion],
                OSArch:[OSArch],
                UserName:[UserName],
                UserHost:[UserHost],
                UserDir:[UserDir]
            }

->{Seheduled} [ServerUrl]/[USERKEY]/[UUID]/performance/UrlMethodCount/[TimeSeriesURL].json ~[JSONArray]
        [
            {
                GET:[count],
                PUT:[count],
                POST:[count],
                DELETE:[count]
            }
        ]

->{Seheduled} [ServerUrl]/[USERKEY]/[UUID]/performance/UrlResponseCount/[TimeSeriesURL].json ~[JSONArray]
        [
            AverageResponseSize:[AverageResponseSize],
            PerformanceSpeed:[PerformanceSpeed],
            RequestRate:[RequestRate],
            ResponseTime:[ResponseTime]
        ]

-> {once} [ServerUrl]/[USERKEY]/[UUID]/Components/jars.json ~[JSONArray]
        [installed jars]